EAD 3 de Programação Web 3. Feito por Gabriel Zanotim Manhani e Vinicius Macena.

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

PUT parcial:
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

http://localhost:8080/novo-conserto

get:

http://localhost:8080/listar-todos

get simples:

http://localhost:8080/dados-simples

get unico: 

http://localhost:8080/conserto/{id}

put parcial:

http://localhost:8080/atualizar-parcial

delete logico: 

http://localhost:8080/conserto/{id}

```