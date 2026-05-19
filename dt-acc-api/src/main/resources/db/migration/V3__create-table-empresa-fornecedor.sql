CREATE TABLE empresa_fornecedor( 
    empresa_id BIGINT NOT NULL,
    fornecedor_id BIGINT NOT NULL,
	
	PRIMARY KEY(empresa_id, fornecedor_id),
	
	CONSTRAINT fk_empresas
        FOREIGN KEY (empresa_id)
        REFERENCES empresas(id),

    CONSTRAINT fk_fornecedores
        FOREIGN KEY (fornecedor_id)
        REFERENCES fornecedores(id)
);
