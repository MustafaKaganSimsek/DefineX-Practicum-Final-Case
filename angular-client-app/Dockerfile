# stage 1

FROM node:alpine AS build
WORKDIR /app
COPY . .
RUN npm ci && npm run build

# stage 2

FROM nginx:alpine
COPY --from=build /app/dist/angular-client-app /usr/share/nginx/html
EXPOSE 80
