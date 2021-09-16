
function calcula(){
var woct1, woct2, woct3, woct4, wnredes, wnhosts, wclase, werror;
var nbits, wmoct1, wmoct2, wmoct3, wmoct4, i;

/* Obtenemos los datos del formulario */
woct1 = parseInt(document.getElementById('oct1').value);
woct2 = parseInt(document.getElementById('oct2').value);
woct3 = parseInt(document.getElementById('oct3').value);
woct4 = parseInt(document.getElementById('oct4').value);
wnredes = parseInt(document.getElementById('nredes').value);

/* inicializamos las variables y deyterminamos la clase de la red en función del primer octeto*/
wclase = ""
werror = false;
if ((woct1 >= 1) && (woct1 <= 126)) wclase="A";
if ((woct1 >= 128) && (woct1 <= 191)) wclase="B";
if ((woct1 >= 192) && (woct1 <= 223)) wclase="C";
if (wclase == ""){
alert ("ERROR CON CON EL TIPO DE RED");
document.getElementById('oct1').focus();
werror = true;
}
if (wnredes ==0){
alert ("ERROR CON CON EL NÚMERO DE SUBREDES");
document.getElementById('nredes').focus();
werror = true;
}
document.getElementById('tipoRed').value = wclase;

// Determinamos la potencia de 2 que es >= que el número de redes introducidas
// para saber el número de bits de la máscara
// Así para 7 redes necesitamos 3 bits
nbits = 0
while (Math.pow(2,nbits) < wnredes){
nbits++
}

//Vamos a calcular la máscara en función del tipo de red

switch(wclase){
case 'A':
// Máscara por defecto sin subneting
wmoct1 = 255;
wmoct2 = 0;
wmoct3 = 0;
wmoct4 = 0;
// bits necesarios para la máscara (los de la clase+subneting)
document.getElementById('mbits').value = 8 + nbits;
//Debemos diferenciar si el número de redes implica 1, 2 o 3 bytes
if (nbits >= 16){
// necesitamos 2 bytes para las subredes
nbits = nbits - 16;
wmoct2 = 255;
wmoct3 = 255;
//Calculamos los bits que necesitamos para el subneting
wmoct4 = Math.pow(2,nbits) - 1;
//los pasamos a la iquierda o parte MSB mediante desplazamiento
wmoct4 = wmoct4 << (8-nbits);

}
else {
if (nbits >= 8){
// necesitamos 2 bytes para las subredes
nbits = nbits - 8;
wmoct2 = 255;
wmoct4 = 0;
//Calculamos los bits que necesitamos para el subneting
wmoct3 = Math.pow(2,nbits) - 1;
//los pasamos a la iquierda o parte MSB mediante desplazamiento
wmoct3 = wmoct3 << (8-nbits);

}
else{
// necesitamos 1 byte para las subredes, es como el caso C
//Calculamos los bits que necesitamos para el subneting
wmoct2 = Math.pow(2,nbits) - 1;
//los pasamos a la iquierda o parte MSB mediante desplazamiento
wmoct2 = wmoct2 << (8-nbits);
wmoct3 = 0;
wmoct4 = 0;
}
// end-else
}
break;
case 'B':
// Máscara por defecto sin subneting
wmoct1 = 255;
wmoct2 = 255;
wmoct3 = 0;
wmoct4 = 0;
// bits necesarios para la máscara (los de la clase+subneting)
document.getElementById('mbits').value = 16 + nbits;
//Debemos diferenciar si el número de redes implica 1 o 2 bytes
if (nbits >= 8){
// necesitamos 2 bytes para las subredes
nbits = nbits - 8;
wmoct3 = 255;
//Calculamos los bits que necesitamos para el subneting
wmoct4 = Math.pow(2,nbits) - 1;
//los pasamos a la iquierda o parte MSB mediante desplazamiento
wmoct4 = wmoct4 << (8-nbits);

}
else{
// necesitamos 1 byt para las subredes, es como el caso C
//Calculamos los bits que necesitamos para el subneting
wmoct3 = Math.pow(2,nbits) - 1;
//los pasamos a la iquierda o parte MSB mediante desplazamiento
wmoct3 = wmoct3 << (8-nbits);
}

break;
case 'C':
// Máscara por defecto sin subneting
wmoct1 = 255;
wmoct2 = 255;
wmoct3 = 255;
wmoct4 = 0;
// En este caso no es necesario diferenciar ya que únicamente disponemos de 1 byte para las subredes
// bits necesarios para la máscara (los de la clase+subneting)
document.getElementById('mbits').value = 24 + nbits;
//Calculamos los bits que necesitamos para el subneting
wmoct4 = Math.pow(2,nbits) - 1;
//los pasamos a la iquierda o parte MSB mediante desplazamiento
wmoct4 = wmoct4 << (8-nbits);



case 'D':
// Máscara por defecto sin subneting
wmoct1 = 0;
wmoct2 = 0;
wmoct3 = 0;
wmoct4 = 0;
// En este caso no es necesario diferenciar ya que únicamente disponemos de 1 byte para las subredes
// bits necesarios para la máscara (los de la clase+subneting)
document.getElementById('mbits').value = 0 + nbits;
//Calculamos los bits que necesitamos para el subneting
wmoct4 = Math.pow(2,nbits) - 1;
//los pasamos a la iquierda o parte MSB mediante desplazamiento
wmoct4 = wmoct4 << (8-nbits);




case 'E':
// Máscara por defecto sin subneting
wmoct1 = 0;
wmoct2 = 0;
wmoct3 = 0;
wmoct4 = 0;
// En este caso no es necesario diferenciar ya que únicamente disponemos de 1 byte para las subredes
// bits necesarios para la máscara (los de la clase+subneting)
document.getElementById('mbits').value = 0 + nbits;
//Calculamos los bits que necesitamos para el subneting
wmoct4 = Math.pow(2,nbits) - 1;
//los pasamos a la iquierda o parte MSB mediante desplazamiento
wmoct4 = wmoct4 << (8-nbits);

break;
}
document.getElementById('moct1').value = wmoct1;
document.getElementById('moct2').value = wmoct2;
document.getElementById('moct3').value = wmoct3;
document.getElementById('moct4').value = wmoct4;


}
