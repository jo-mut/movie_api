# Use the official Java 8 image as the base image
FROM openjdk:8-jre-slim

# Set the working directory in the container
WORKDIR /app

# Install dependencies like curl and git if needed (for Leiningen)
RUN apt-get update && \
    apt-get install -y curl git

# Install Leiningen (build tool for Clojure)
RUN curl -O https://raw.githubusercontent.com/technomancy/leiningen/stable/bin/lein && \
    chmod +x lein && \
    mv lein /usr/local/bin/

# Copy the project.clj and src/ into the container
COPY project.clj /app/
COPY src /app/src

# Expose the port your Clojure app will listen on
EXPOSE 3000

# Run the Clojure app using Leiningen
CMD ["lein", "run"]
