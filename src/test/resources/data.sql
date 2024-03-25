INSERT INTO tb_clientes (ID, NOME, EMAIL, FONE, LOGRADOURO, NUMERO, COMPLEMENTO, BAIRRO, CIDADE, UF, CEP)
VALUES
    ('5f789b39-4295-42c1-a65b-cfca5b987db2', 'Adam', 'adam@example.com', '123456789', 'Rua ABC', '123', 'Apto 1', 'Centro', 'Cidade A', 'UF', '12345-678'),
    ('65b1bbee-c784-4457-be6d-d00b0be5c9e0', 'Diva', 'diva@example.com', '987654321', 'Rua XYZ', '456', 'Casa 2', 'Bairro B', 'Cidade B', 'UF', '98765-432'),
    ('592ac344-9f12-40cd-8ed9-1fde6ad9006e', 'Dany', 'dany@example.com', '456123789', 'Rua LMN', '789', 'Sala 3', 'Vila C', 'Cidade C', 'UF', '45678-901'),
    ('a02bc76a-9e20-4557-be2b-ee4d5b6fa636', 'Eddy', 'eddy@example.com', '789123456', 'Rua UVW', '1011', 'Galpão 4', 'Distrito D', 'Cidade D', 'UF', '78901-234'),
    ('85d16404-0af9-46ed-bdf4-5c2eadedab94', 'Vick', 'vick@example.com', '321654987', 'Rua HIJ', '1213', 'Sobrado 5', 'Setor E', 'Cidade E', 'UF', '32109-876');

INSERT INTO tb_restaurantes (id, razao_social, nome_fantasia, tipo_cozinha, capacidade, hora_abertura, hora_encerramento, logradouro, numero, complemento, bairro, cidade, uf, cep)
VALUES
    ('5f789b39-4295-42c1-a65b-cfca5b987db2', 'Restaurante A', 'Fantasia A', 'Cozinha A', 50, '10:00:00', '22:00:00', 'Rua 1', '123', 'Apto 1', 'Centro', 'Cidade A', 'UF', '12345-678'),
    ('65b1bbee-c784-4457-be6d-d00b0be5c9e0', 'Restaurante B', 'Fantasia B', 'Cozinha B', 40, '09:00:00', '23:00:00', 'Rua 2', '456', 'Casa 2', 'Bairro B', 'Cidade B', 'UF', '98765-432'),
    ('592ac344-9f12-40cd-8ed9-1fde6ad9006e', 'Restaurante C', 'Fantasia C', 'Cozinha C', 60, '08:00:00', '21:00:00', 'Rua 3', '789', 'Sala 3', 'Vila C', 'Cidade C', 'UF', '45678-901');

INSERT INTO tb_mesas (id, id_restaurante, lugares)
VALUES
    ('1a3b8f0f-77ab-474e-8102-70d419b9602d', '5f789b39-4295-42c1-a65b-cfca5b987db2', 4),
    ('2b4c6d8e-fa08-4138-8e32-93523f022d17', '5f789b39-4295-42c1-a65b-cfca5b987db2', 6),
    ('3c5d7e9f-16ab-481c-b899-9e94e1e94c0b', '65b1bbee-c784-4457-be6d-d00b0be5c9e0', 5),
    ('4e6f8a0b-27cd-4b8f-b04d-4a91f6f53167', '592ac344-9f12-40cd-8ed9-1fde6ad9006e', 8);
