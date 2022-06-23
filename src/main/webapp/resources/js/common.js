  /* 모달 및 팝업 닫기 */
    $('.btn--close').on('click', function () {
        $(this).parent().parent().stop().fadeOut(100);
    });

    $(document).on('click', '.closeWindow', function () {
        $(this).parent().parent().parent().stop().fadeOut(100);
    });

    $('.bg-black').on('click', function () {
        $(this).parent().stop().fadeOut(100);
    });

    $('body,html').keydown(function (e) {
        if (e.keyCode == 27 || e.which == 27) {
            if ($('.section--modal').css('display') == 'flex') {
                $('.section--modal').fadeOut(200);
            } else if ($('.section--alert').css('display') == 'flex') {
                $('.section--alert').fadeOut(200);
            }

        }
    })
    /* //모달 및 팝업 닫기 */
    
    
    
    
    
    function openAlert(alertWindow) {
    $(alertWindow).siblings('.section--alert').css('display', 'flex');
}