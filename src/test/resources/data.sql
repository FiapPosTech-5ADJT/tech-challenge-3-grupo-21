INSERT INTO restaurante (nome, cep, logradouro, numero, complemento, bairro, cidade, estado, pais, tipo_restaurante, capacidade, horario_abertura, horario_fechamento, created_at, updated_at)
VALUES ('Restaurante A', '12345678', 'Rua A', '100', 'Apto 1', 'Bairro A', 'Cidade A', 'Estado A', 'Pais A', 'Tipo A', 50, '08:00:00', '22:00:00', '2023-01-01T08:00:00', '2023-01-01T08:00:00');

INSERT INTO restaurante (nome, cep, logradouro, numero, complemento, bairro, cidade, estado, pais, tipo_restaurante, capacidade, horario_abertura, horario_fechamento, created_at, updated_at)
VALUES ('Restaurante B', '23456789', 'Rua B', '200', 'Apto 2', 'Bairro B', 'Cidade B', 'Estado B', 'Pais B', 'Tipo B', 75, '09:00:00', '23:00:00', '2023-01-02T09:00:00', '2023-01-02T09:00:00');

INSERT INTO restaurante (nome, cep, logradouro, numero, complemento, bairro, cidade, estado, pais, tipo_restaurante, capacidade, horario_abertura, horario_fechamento, created_at, updated_at)
VALUES ('Restaurante C', '34567890', 'Rua C', '300', 'Apto 3', 'Bairro C', 'Cidade C', 'Estado C', 'Pais C', 'Tipo C', 100, '10:00:00', '20:00:00', '2023-01-03T10:00:00', '2023-01-03T10:00:00');

INSERT INTO dias_semana (restaurante_id, dia_semana)
VALUES (1, 'SEGUNDA'), (1, 'TERCA'), (1, 'QUARTA'), (1, 'QUINTA'), (1, 'SEXTA'), (1, 'SABADO'), (1, 'DOMINGO');

INSERT INTO dias_semana (restaurante_id, dia_semana)
VALUES (2, 'SEGUNDA'), (2, 'TERCA'), (2, 'QUARTA'), (2, 'QUINTA'), (2, 'SEXTA'), (2, 'SABADO');

INSERT INTO dias_semana (restaurante_id, dia_semana)
VALUES (3, 'SEGUNDA'), (3, 'TERCA'), (3, 'QUARTA'), (3, 'QUINTA'), (3, 'SEXTA');

INSERT INTO usuarios (cpf, nome, ddd, telefone, email, data_cadastro)
VALUES ('12345678901', 'Maria da Silva', '11', '912345678', 'maria.silva@example.com', '2023-01-01');

INSERT INTO usuarios (cpf, nome, ddd, telefone, email, data_cadastro)
VALUES ('23456789012', 'João Pereira', '21', '923456789', 'joao.pereira@example.com', '2023-01-02');

INSERT INTO usuarios (cpf, nome, ddd, telefone, email, data_cadastro)
VALUES ('34567890123', 'Ana Souza', '31', '934567890', 'ana.souza@example.com', '2023-01-03');

INSERT INTO reservas (restaurante_id, quantidade_pessoas, data_hora_inicio, data_hora_fim, status)
VALUES (1, 4, '2023-01-04T12:00:00', '2023-01-04T14:00:00', 'AGENDADO');

INSERT INTO reservas (restaurante_id, quantidade_pessoas, data_hora_inicio, data_hora_fim, status)
VALUES (1, 4, '2023-01-05T12:00:00', '2023-01-05T14:00:00', 'CONCLUIDA');

