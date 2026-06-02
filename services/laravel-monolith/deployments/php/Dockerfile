FROM php:8.5-fpm

RUN apt-get update && apt-get install -y \
    git \
    curl \
    zip \
    unzip \
    libpq-dev

RUN docker-php-ext-install \
    pdo \
    pdo_pgsql \
    pgsql \
    bcmath

COPY --from=composer:2.10 /usr/bin/composer /usr/local/bin/composer

WORKDIR /var/www

COPY ../src /var/www/

RUN COMPOSER_ALLOW_SUPERUSER=1 composer install --no-interaction --optimize-autloloader

RUN php artisan key:generate

EXPOSE 9000

CMD ["php-fpm"]