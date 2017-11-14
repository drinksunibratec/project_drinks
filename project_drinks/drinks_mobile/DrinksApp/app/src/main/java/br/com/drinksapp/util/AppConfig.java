package br.com.drinksapp.util;

/**
 * Created by Renevalda Maria on 18/09/2017.
 */
public class AppConfig {


    // Server user login url
    public static String URL_LOGIN = "http://www.comunidademaanaim.org.br/drinks_service/login.php";

    // Server user register url
    public static String URL_REGISTER = "http://www.comunidademaanaim.org.br/drinks_service/register.php";

    public static final String URL_LISTA_ESTABELECIMENTOS = "https://www.comunidademaanaim.org.br/drinks_service/lista_estabelecimentos.php";
    public static final String URL_CONSULTA_ESTABELECIMENTO = "https://www.comunidademaanaim.org.br/drinks_service/lista_estabelecimento_por_id.php";
    public static final String URL_LISTA_PRODUTOS_POR_ESTABELECIMENTO = "https://www.comunidademaanaim.org.br/drinks_service/lista_produtos_por_estabelecimento.php";
    public static final String URL_INSERIR_PEDIDO = "https://www.comunidademaanaim.org.br/drinks_service/insert_pedido.php";
    public static final String URL_BUCAR_PEDIDOS_CLIENTE = "https://www.comunidademaanaim.org.br/drinks_service/lista_pedido_de_usuario.php";
    public static final String URL_INSERIR_PRODUTOS_NO_PEDIDO = "https://www.comunidademaanaim.org.br/drinks_service/inserir_produtos_pedido.php";
    public static final String URL_LISTAR_PRODUTOS_NO_PEDIDO = "https://www.comunidademaanaim.org.br/drinks_service/lista_produtos_por_pedido.php";



    public static final String APP_KEY = "6CTKkcmHuqSnl90jz58KxXe5DMDld9gi";

    public static final String CORREIOS_APP_KEY = "Z6Urr77Dz3H9UXv6j6buyC1FsLV8kPoobq8ho1LqXB4yEVyh";

    public static final String URL_CORREIOS = "https://webmaniabr.com/api/1/cep/";
    public static final String COMPLEMENTO_URL_CORREIOS = "/?app_key=" + APP_KEY + "&app_secret=" + CORREIOS_APP_KEY;
}
