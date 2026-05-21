CREATE TABLE fornecedores( 
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    tipo_pessoa VARCHAR(8) NOT NULL,
    cnpj VARCHAR(14) UNIQUE,
    cpf VARCHAR(11) UNIQUE,
    nome VARCHAR(120),
    cep VARCHAR(9),
    email VARCHAR(100),
    rg VARCHAR(20),
    data_nascimento DATE
);
