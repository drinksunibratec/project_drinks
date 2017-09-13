/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function (e) {
        $('.ExibirLightBox').hover(function (e) {
            $(".lightbox").css("display", "block");
        }
    );
    });
    
    
//JavaScript do Menu
$(".nav").addClass("fechada");
	$(".nav").after("<a href=\"#\" class=\"nav-toggle\">Menu</a>");
	
	$(".nav-toggle").click(function() {
		var altura = $(".listaNav").height();
		if($(".nav").hasClass("fechada")) {
			$(".nav").animate({height:altura},{queue:false, duration:200}).removeClass("fechada");
		}
		else {
			$(".nav").animate({height:"0px"},{queue:false, duration:200}).addClass("fechada");
		}
	});
	
	$(window).resize(function() {
		var tamanhoViewport = $(window).width();
		if (tamanhoViewport > 800) {
			$(".nav").css("height","auto").addClass("fechada");
		} else {
			$(".nav").css("height","0px").addClass("fechada");
		}
	});
