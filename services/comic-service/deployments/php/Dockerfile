FROM php:8.5-fpm

RUN apt-get update && apt-get install -y \
    git \
    curl \
    zip \
    unzip \
    docker-php-ext-install pdo pdo_pgsql pgsql

WORKDIR /var/www
