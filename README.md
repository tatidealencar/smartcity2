Configuração
=====================================================================

- Instalar Homebrew
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"

- Instalar GIT:
brew update
brew install git
brew install git-gui

-  Para macOS
Entrar em keychain e criar uma pro GitHub com um token

- Instalar VSCode

- No Terminal do VS Code, clonar projeto:
git config --global user.name "nome"
git config --global user.email nome@gmail.com
git clone https://github.com/tatidealencar/smartcity2.git

- Instalar MySQLWorkbench
iniciar BD
rodar script de criação do banco de dados

- Rodar a aplicação
cd /Caminho/Para/Projeto/smartcity2
mvn clean install
mvn tomcat7:run

Uso do Simulador
=====================================================================

Passo a passo para execução de cenários no simulador.

Opção A: 
1) Preencher:
    as coordenadas e o status inicial do semáforo;
    as coordenadas do pedestre;
    e as coordenadas e velocidade do veículo. 
2) Escolher a opção de observer (i.e., carro, pedestre, ambos). Se não for preenchido, por padrão será ambos.
3) Deixar a condição de risco sem preencher.
4) Clicar no botão Simular.

Opção B: 
1) Ignorar o preenchimento de coordenadas, status do semáforo ou velocidade do veículo.
2) Escolher a opção de observer (i.e., carro, pedestre, ambos). Se não for preenchido, por padrão será ambos.
3) Escolher a opção de condição de risco. Nesse caso, quaisquer alterações nos campos de texto serão ignoradas.
5) Clicar no botão Simular.

Aviso: Notificações sonoras só são emitidas para carro ou pedestre. O teste de ambos só emite notificações visuais. 
Observação: Na versão atual as notificações visuais anteriores só desaparecem se novas notificações forem geradas para o observer.

Funcionamento do sistema
=====================================================================

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
            - se há tempo hábil: chamar atenção e mandar não atravessar
        - veículo:
            - se colisão é inevitável: chamar resgate
            - se há tempo hábil: mandar frear
    
