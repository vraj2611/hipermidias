<!doctype html>
<html lang="pt-br">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/estilo.css">
    <link rel="stylesheet" href="css/autocomplete.css">

    <!-- Optional JavaScript - jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="bootstrap/jquery-3.3.1.min.js"></script>
    <script src="bootstrap/popper.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>

    <!-- HABILITAR MENU IR PARA PAGINAS -->
    <script type="text/javascript">

      function ir_para_pagina(destino){
        $.ajax({url: destino,
          success: function(data){ $('#div_conteudo').html(data);},
        });
      }

      $(document).ready (function() {
        //Carrega a pagina inicial
        $.ajax({url: 'pag_intro.php',
          success: function(data){ $('#div_conteudo').html(data);}
        });
      });
    </script>

    <title>Portal Integração</title>
</head>
<body>
  <div class="container-fluid" style="padding: 0px;">

    <!-- CABEÇALHO -->
    <header class="row no-gutters">
      <div class="col-sm-8 col-md-8 col-lg-6 col-xl-6" >
        <img src="images/bg_header_ioi.png" class="img-fluid" href="#" onclick="ir_para_pagina('pag_intro.php')">
      </div>
      <div class="col-sm-4 col-md-4 col-lg-6 col-xl-4 align-self-end" style="margin-top: 10px;">
        <form autocomplete="off" class="row no-gutters justify-content-center" action="#">
          <div class="autocomplete" >
            <input id="myInput" class="form-control" type="text" name="pesquisarp" placeholder="Pesquisa" style="width: 300px;">
          </div>
          <button type="submit" class="btn btn-outline-success"><img src="images/oie_transparent.png"</button>
        </form>

        <!-- auto complete -->
        <script src="js/autocomplete.js"></script>
        <script>
          var lista = ["DIPs", "Câmbios", "Tarifas"];
          autocomplete(document.getElementById("myInput"), lista);
        </script>

      </div>
    </header>

    <!--Barra de Navegação -->
    <nav class="navbar navbar-expand-md navbar-ioi bg-verdebr" style="padding-top: 0px; padding-bottom: 0px;">
      <a class="navbar-brand btn_ir_pagina" id="pag_intro" href="#">SUB/IOI</a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarNavDropdown">
        <ul class="navbar-nav">
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              EVTE
            </a>
            <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                <a class="dropdown-item" href="#" onclick="ir_para_pagina('pag_dips.php')">DIPs</a>
                <a class="dropdown-item" href="#" onclick="ir_para_pagina('pag_indicadores.php')">Indicadores</a>
                <a class="dropdown-item" href="#" onclick="ir_para_pagina('pag_paineis.php')">Paineis</a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="#" onclick="ir_para_pagina('pag_tarifas.php')">Tarifas</a>
                <a class="dropdown-item" href="#" onclick="ir_para_pagina('pag_cambios.php')">Câmbios</a>
                <a class="dropdown-item" href="#" onclick="ir_para_pagina('pag_carteiraevte.php')">Carteira de EVTE</a>
            </div>
          </li>
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              Parceria
            </a>
            <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                <a class="dropdown-item" href="#">DIPs</a>
                <a class="dropdown-item" href="#">Indicadores</a>
                <a class="dropdown-item" href="#">Paineis</a>
            </div>
          </li>
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              Recurso Crítico
            </a>
            <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                <a class="dropdown-item" href="#">DIPs</a>
                <a class="dropdown-item" href="#">Indicadores</a>
                <a class="dropdown-item" href="#">Paineis</a>
            </div>
          </li>
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              Apropriação
            </a>
            <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                <a class="dropdown-item" href="#">DIPs</a>
                <a class="dropdown-item" href="#">Indicadores</a>
                <a class="dropdown-item" href="#">Paineis</a>
            </div>
          </li>
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              PNG
            </a>
            <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                <a class="dropdown-item" href="#">DIPs</a>
                <a class="dropdown-item" href="#">Resultados</a>
                <a class="dropdown-item" href="#">Paineis</a>
            </div>
          </li>
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              PAN
            </a>
            <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                <a class="dropdown-item" href="#">DIPs</a>
                <a class="dropdown-item" href="#">Resultados</a>
                <a class="dropdown-item" href="#">Paineis</a>
            </div>
          </li>
        </ul>
      </div>
    </nav>
    <main class="row no-gutters">

      <!-- AREA CONTEUDO -->
      <section id="div_conteudo" class="col-md-9 col-lg-9 col-xl-8">
          <div id="carregando" style="display:none">
              <h1><br><br>Carregando...</h1>
              <div class="progress-bar progress-bar-striped progress-bar-animated" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width: 50%"></div>
          </div>
      </section>

      <!-- BARRA LATERAL -->
      <aside class="col-md-3 col-lg-3 col-xl-2" style="padding: 5px;">
        <nav class="barra-lateral">
          <hr>
          <p class="titulo-barra-lateral">ACESSO RÁPIDO</p>
          <p class="itens-barra-lateral"><a class="itens-barra-lateral" href="#" onclick="ir_para_pagina('pag_cambios.php')">Cambios</a></p>
          <p class="itens-barra-lateral"><a class="itens-barra-lateral" href="#" onclick="ir_para_pagina('pag_tarifas.php')">Tarifas</a></p>
          <p class="itens-barra-lateral"><a class="itens-barra-lateral" href="#" onclick="ir_para_pagina('pag_dips.php')">DIPs</a></p>
          <p class="itens-barra-lateral"><a class="itens-barra-lateral" href="#" onclick="ir_para_pagina('pag_resultadospng.php')">Resultados PNG</a></p>
          <p class="itens-barra-lateral"><a class="itens-barra-lateral" href="#" onclick="ir_para_pagina('pag_indicadores.php')">Indicadores</a></p>
        </nav>
        <nav class="barra-lateral"
          <hr>
          <p class="titulo-barra-lateral">LINKS ÚTEIS</p>
          <p class="itens-barra-lateral"><a class="itens-barra-lateral" href="http://plancor.petrobras.biz" target="_blank">PLANCOR</a></p>
          <p class="itens-barra-lateral"><a class="itens-barra-lateral" href="http://portalpetrobras.petrobras.com.br" target="_blank">Portal Petrobras</a></p>
          <p class="itens-barra-lateral"><a class="itens-barra-lateral" href="http://aplicacoespetrobras.petrobras.com.br" target="_blank">Aplicações Petrobras</a></p>
          <p class="itens-barra-lateral"><a class="itens-barra-lateral" href="https://seguro7.petrobras.com.br/cinn" target="_blank">Novos Negócios</a></p>
          <p class="itens-barra-lateral"><a class="itens-barra-lateral" href="http://gissub2" target="_blank">GISUB</a></p>
          <hr>
        </nav>
      </aside>
      <div class="col-xl-2"></div>
    </main>

    <!-- RODAPE -->
    <footer class="footer">
        <p class="footer-text">Canal Integração - v0.9 &nbsp; | &nbsp; Petrobras © 2018</p>
    </footer>

  </div>
</body>
</html>
