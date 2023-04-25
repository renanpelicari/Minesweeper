# Dockerfile

# Use the Node.js 18.7.0 Alpine base image
FROM node:18.7.0-alpine

# Set the working directory
WORKDIR /app

# Copy package.json and package-lock.json to the working directory
COPY package.json package-lock.json ./

# Install dependencies with npm
RUN npm ci

# Copy the rest of the application code to the working directory
COPY . .

# Expose a port for the application to listen on
EXPOSE 3000

# Run the application
CMD ["npm", "start"]