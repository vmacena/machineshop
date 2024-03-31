CREATE TABLE conserto (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          data_de_entrada VARCHAR(255),
                          data_de_saida VARCHAR(255),
                          mecanico_nome VARCHAR(255),
                          mecanico_anos_de_experiencia INT,
                          veiculo_marca VARCHAR(255),
                          veiculo_modelo VARCHAR(255),
                          veiculo_ano INT
);