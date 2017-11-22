$('.hr_uers').mouseenter(function() {
	$('.hs_fixed').show();
})
$('.hr_uers').mouseleave(function() {
	$('.hs_fixed').mouseenter(function() {
		$('.hs_fixed').show();
	})
})
$('.hr_menu').mouseenter(function() {
	$('.hs_fixed').hide();
})
$('.hr_rt').mouseenter(function() {
	$('.hs_fixed').hide();
})

$('.hs_fixed').mouseleave(function() {
	$('.hs_fixed').hide();
})
$('.hr_menu li').click(function() {
	$(this).addClass('hu_act').siblings().removeClass('hu_act');
})