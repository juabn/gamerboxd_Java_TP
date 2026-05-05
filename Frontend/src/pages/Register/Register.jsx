
import miImagen from '../../assets/imagenlogin.jpg';

import './Register.css'

function Hola(){
	
	
	return(
		
		<div className="contenedorPrincipal">
		
		<div className="primerDiv">
		
		<div className="divlogin">
		
		<p className='bienvenido'> Bienvenido </p>
		
		<div className='inputs'>
		<input className='input' type="text" placeholder='Ingrese su usuario'/>
		<input className='input' type="password" placeholder='Ingrese su contraseña' />
		
		</div>
		
		<div className='divboton'>
		<button className='ingresar'> Ingresar </button>
		</div>
		<p className='aviso'> ¿No tienes cuenta? </p>
		<p className='aviso'> ¿Olvidaste tu contraseña? </p>
		
		
		 </div>
		
		
		
		</div>
		
		
		
		
		
		</div>
	);
}

export default Hola;