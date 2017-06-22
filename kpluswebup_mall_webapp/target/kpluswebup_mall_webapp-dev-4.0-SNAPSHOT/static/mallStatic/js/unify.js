// JavaScript Document
$(function(){
	// check placeholder
	if(!placeholderSupport()){   
		$('[placeholder]').focus(function() {
			var input = $(this);
			if (input.val() == input.attr('placeholder')) {
				input.val('');
				input.css("color","#595959")
				input.removeClass('placeholder');
			}
		}).blur(function() {
			var input = $(this);
			if (input.val() == '' || input.val() == input.attr('placeholder')) {
				input.addClass('placeholder');
				input.css("color","#999999")
				input.val(input.attr('placeholder'));
			}
		}).blur();
	};
})
	
function placeholderSupport() {
    return 'placeholder' in document.createElement('input');
}