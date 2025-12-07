# Build Angular app
FROM node:18-alpine AS build
WORKDIR /app
COPY package*.json angular.json tsconfig*.json ./
# copy source
COPY src ./src
RUN npm ci
RUN npm run build -- --configuration=production

# NGINX stage
FROM nginx:stable-alpine
COPY --from=build /app/dist /usr/share/nginx/html
# custom nginx config to proxy /api and /auth (see nginx.conf)
COPY nginx.conf /etc/nginx/nginx.conf
COPY ssl/selfsigned.crt /etc/ssl/certs/selfsigned.crt
COPY ssl/selfsigned.key /etc/ssl/private/selfsigned.key
EXPOSE 80 443
CMD ["nginx", "-g", "daemon off;"]