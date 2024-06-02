EAD 3 de Programação Web 3. Feito por Gabriel Zanotim Manhani e Vinicius Macena.

Forma de acesso: Fazer requisição de login (POST) com o formato disponibilado a baixo. Feito isso, o acesso para as demais requisições estará liberado. 

Formato da requisição POST // Login:
```
{
            "login": "admin",
            "senha": "123456"
        }
```

Formato da requisição POST:
```
{
            "dataDeEntrada": "08/03/2024",
            "dataDeSaida": "25/03/2024",
            "mecanicoResponsavel": {
                "nome": "Vinicius Macena",
                "anosDeExperiencia": 21
            },
            "veiculo": {
                "marca": "VW",
                "modelo": "Gol",
                "ano": "2000",
                "cor": "vermelho"
            }
        }
```

PATCH:
```
{
            "id": 10,
            "dataDeSaida": "10/03/2024",
            "nomeMecanico": "Gabriel Manhani",
            "anosExperiencia": 10
        }
```


Requisições:

```

post:

http://localhost:8080/login

post:

http://localhost:8080/conserto

get:

http://localhost:8080/consertos

get ativos:

http://localhost:8080/consertos-ativos

get simples:

http://localhost:8080/consertos-paginacao

get unico: 

http://localhost:8080/conserto/{id}

patch:

http://localhost:8080/conserto/desativar/{id}

delete logico: 

http://localhost:8080/conserto/{id}

```
