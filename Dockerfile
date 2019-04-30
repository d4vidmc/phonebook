FROM ubuntu:18.04

MAINTAINER d4vidmc <d4vidangelmc@gmail.com>

ENV DEBIAN_FRONTEND=noninteractive

RUN apt-get update && apt-get install -yq --no-install-recommends \
    apt-utils \
    curl \
    wget \
    unzip \
    git \
    apache2 \
    libapache2-mod-php7.2 \
    php7.2-cli \
    php7.2-json \
    php7.2-curl \
    php7.2-fpm \
    php7.2-gd \
    php7.2-ldap \
    php7.2-mbstring \
    php7.2-mysql \
    php7.2-soap \
    php7.2-sqlite3 \
    php7.2-xml \
    php7.2-zip \
    php7.2-intl \
    php-imagick \
    openssl \
    nano \
    graphicsmagick \
    imagemagick \
    ghostscript \
    mysql-client \
    iputils-ping \
    locales \
    ca-certificates \
    && apt-get clean && rm -rf /var/lib/apt/lists/*

# Install composer
RUN curl -sS https://getcomposer.org/installer | php -- --install-dir=/usr/local/bin --filename=composer

# Set locales
RUN locale-gen en_US.UTF-8 es_ES.UTF-8 es_BO.UTF-8

EXPOSE 80 443

WORKDIR /var/www/html

RUN rm index.html

RUN rm -rf /var/www/*

RUN wget https://binaries.sonarsource.com/Distribution/sonar-scanner-cli/sonar-scanner-cli-3.3.0.1492-linux.zip

RUN unzip sonar-scanner-cli-3.3.0.1492-linux.zip

ENV PATH="/var/www/sonar-scanner-3.3.0.1492-linux:${PATH}"

CMD apachectl -D FOREGROUND 

