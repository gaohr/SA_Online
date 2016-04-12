
var a;
function t(){
    var num1=parseFloat(document.form1.textfield.value);
    var num2=parseFloat(document.form1.textfield2.value);
    var result;
    switch(a){
        case "1":result=num1+num2;document.form1.textfield3.value=result;a="0";break;
        case "2":result=num1-num2;document.form1.textfield3.value=result;a="0";break;
        case "3":result=num1*num2;document.form1.textfield3.value=result;a="0";break;
        case "4":result=num1/num2;document.form1.textfield3.value=result;a="0";break;
        default:document.form1.textfield3.value=("请问您要执行哪种运算？");break;
    }
}
function plus(){
    a="1";
}
function subtract(){
    a="2";
}
function multiply(){
    a="3";
}
function divide(){
    a="4";
}
