# --- Stage 1: Build the React app ---
FROM node:18-alpine AS build

WORKDIR /app

# Install dependencies cleanly
COPY package.json package-lock.json ./
RUN npm ci

COPY . .
RUN npm run build    # produces /dist

# --- Stage 2: Serve with Nginx ---
FROM nginx:alpine

# Remove default nginx config
RUN rm /etc/nginx/conf.d/default.conf

# Copy our custom nginx config
COPY nginx.conf /etc/nginx/conf.d/default.conf

# Copy the built frontend
COPY --from=build /app/dist /usr/share/nginx/html

EXPOSE 80

# Force foreground execution to avoid shutdown (Docker container exit)
CMD ["nginx", "-g", "daemon off;"]
