down:
	docker-compose down && rm -r mysql/data

up:
	docker-compose up

db:
	docker-compose exec db mysql -u jpauser -p

