Funcionamento do sistema

- Smartphones
    - localização
    
- Veículo
    - localização
    - velocidade

- Semáforo
    - localização
    - status


- Controller
    - get dados:
        - fica recebendo dados de localização do smartphone, veículo e semáforo se estiverem on (semáforo está sempre on)
        - se veículo estiver on, pegar também a velocidade
    - com os dados:
        - calcular tempo de frenagem
        - calcular tempo para colisão entre pedestre e veículo
    - notificações:
        - pedestre:
            - se colisão é inevitável: chamar resgate
            - se há tempo hábil: mandar parar, prosseguir ou voltar
        - veículo:
            - se há tempo hábil: mandar frear
    
