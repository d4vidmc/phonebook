FROM php:7.1-apache
#Install git
RUN apt-get update && apt-get install -yq --no-install-recommends \
    curl \
    git \
    zip \
    unzip \
    ca-certificates \
    && apt-get clean && rm -rf /var/lib/apt/lists/*
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
RUN chown -R www-data:www-data /var/www/html
RUN wget -p /var/www/ https://binaries.sonarsource.com/Distribution/sonar-scanner-cli/sonar-scanner-cli-3.3.0.1492-linux.zip
RUN unzip sonar-scanner-cli-3.3.0.1492-linux.zip -d /var/www/
EXPOSE 80