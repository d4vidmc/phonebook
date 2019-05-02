FROM php:7.1-apache
#Install git
RUN apt-get update && apt-get install -yq --no-install-recommends \
    apt-utils \
    curl \
    git \
    zip \
    unzip \
    wget \
    sudo \
    gnupg \
    ca-certificates \
    && apt-get clean && rm -rf /var/lib/apt/lists/*
RUN curl -sL https://deb.nodesource.com/setup_10.x  | bash -
RUN apt-get install -y nodejs
RUN docker-php-ext-install pdo pdo_mysql mysqli mbstring tokenizer 
RUN a2enmod rewrite
# config Dev env PHP
RUN mv /usr/local/etc/php/php.ini-development /usr/local/etc/php/php.ini
RUN sed -i -e 's/^error_reporting\s*=.*/error_reporting = E_ALL/' /usr/local/etc/php/php.ini
RUN sed -i -e 's/^display_errors\s*=.*/display_errors = On/' /usr/local/etc/php/php.ini
RUN sed -i -e 's/^zlib.output_compression\s*=.*/zlib.output_compression = Off/' /usr/local/etc/php/php.ini

#Install Composer
RUN php -r "copy('https://getcomposer.org/installer', 'composer-setup.php');"
RUN php composer-setup.php --install-dir=. --filename=composer
RUN mv composer /usr/local/bin/
COPY . /var/www/html/
RUN chmod -R 755 /var/www/html/
EXPOSE 80