A aplicação BANK é uma solução que promove facilidade e segurança para a gestão financeira, 
tanto pessoal quanto empresarial. Desse modo, ela traz praticidade aos clientes para realizar 
transações bancárias, bem como auxilia a administração ao proporcionar um maior poder gerencial.
Sendo assim, segue abaixo o diagrama que descreve os endpoints da aplicação:

CLIENTE ------------------------------------------------------------

@Get("/clientes")

@Get("/cliente/{id}")

@Post("/cliente-inserir")

@Put("/cliente-atualizar/{id})

@Delete("/cliente-deletar/{id}")

CONTA CORRENTE ------------------------------------------------------------

@Get("/conta-corrente")

@Get("/conta-corrente/{id}")

@Post("/inserir-conta-corrente")

@Put("/conta-corrente-atualizar/{id}”)

@Delete("/conta-corrente-deletar/{id}")

@Put("/conta-corrente/depositar/{id}/{valor}")

@Put("/conta-corrente/sacar/{id}/{valor}")

@Put("/conta-corrente/transferir/{idOrigen}/{idDestino}/{valor}")



AGÊNCIA ------------------------------------------------------------

@Get("/agencias")

@Get("/agencia/{id}")

@Post("/agencia-inserir")

@Put("/agencia-atualizar/{id}")

@Delete("/agencia/{id}")

EXTRATO ------------------------------------------------------------

@Get("/extrato")

