down:
	docker-compose down && rm -r mysql/data

up:
	docker-compose up

restart:
	make down && make up

db:
	docker-compose exec db mysql -u jpauser -p

