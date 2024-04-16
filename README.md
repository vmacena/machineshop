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


Requisições:

```
post:

http://localhost:8080/novoconserto

get:

http://localhost:8080/listartodos

get simples:

http://localhost:8080/dados_simples

```