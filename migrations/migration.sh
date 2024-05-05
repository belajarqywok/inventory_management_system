#!/bin/bash

set_defaults() {
    if [ -z "$HOST" ]; then
        HOST="127.0.0.1"
    fi

    if [ -z "$PORT" ]; then
        PORT="3306"
    fi

    if [ -z "$USER" ]; then
        USER="root"
    fi
}

run_mysql_scripts() {
    MYSQL_SCHEMAS="mysql.schemas.sql"
    MYSQL_SEEDERS="mysql.seeders.sql"

    mysql --host="$HOST" --port="$PORT" --user="$USER" < "$MYSQL_SCHEMAS"
    mysql --host="$HOST" --port="$PORT" --user="$USER" < "$MYSQL_SEEDERS"
}

set_defaults
run_mysql_scripts

