# Multi-stage build
FROM eclipse-temurin:17-jdk-jammy AS builder

# Install Node.js and libatomic (required by Gradle's Node.js for WASM)
RUN apt-get update && apt-get install -y curl libatomic1 && \
    curl -fsSL https://deb.nodesource.com/setup_18.x | bash - && \
    apt-get install -y nodejs && \
    rm -rf /var/lib/apt/lists/*

WORKDIR /app

# Copy gradle files
COPY gradle/ gradle/
COPY gradlew gradlew.bat gradle.properties settings.gradle.kts build.gradle.kts ./
COPY build-logic/ build-logic/

# Copy source code
COPY composeApp/ composeApp/
COPY core/ core/
COPY feature/ feature/
COPY detekt.yml ./

# Make gradlew executable
RUN chmod +x gradlew

# Build the application
RUN ./gradlew :composeApp:wasmJsBrowserDistribution --no-daemon

# Production stage
FROM nginx:alpine

# Copy built files
COPY --from=builder /app/composeApp/build/dist/wasmJs/productionExecutable/ /usr/share/nginx/html/

# Copy custom nginx config if needed
# COPY nginx.conf /etc/nginx/nginx.conf

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]
