import './CrearJuegos.css'
import { useState, useEffect } from 'react';
import { useNavigate } from "react-router";
import Select from 'react-select'
import { API_URL } from '../../config';
import AlertMessage from '../../components/AlertMessage/AlertMessage';
import Footer from '../../components/Footer/Footer';

function CrearJuegos(){
	
	const [nombrejuego, setnombrejuegop] = useState("");
	const [descripcion, setdescripcion] = useState("");
	const [imagen, setimagen] = useState("");
	
	const [listaEmpresas, setListaEmpresas] = useState([])
	const [companiasElegidas, setCompaniasElegidas] = useState([]);

	const [alerta, setAlerta] = useState({ tipo: '', mensaje: '' });

	
	
	const navigate = useNavigate();

	const mostrarAlerta = (tipo, mensaje) => {
		setAlerta({ tipo, mensaje });
	};

	const cerrarAlerta = () => {
		setAlerta({ tipo: '', mensaje: '' });
	};

	

	useEffect(() => {
			
		  fetch(`${API_URL}/listaempresas`)
		  
		    .then((Response) => Response.json())
		    .then((dataa) => {
			  const nuevoarray = dataa.map(empresa => ({
			  	
			  	value: empresa.id,
			  	label: empresa.name
			  		
			  }));
			  
			 
			  setListaEmpresas( nuevoarray);
			  
		      
		    })
		    .catch((error) => console.error("Error cargando empresas:", error));
		}, []);
		
		
		const manejarCambioOpcion = (opcionesSeleccionadas) => {
		    
		    setCompaniasElegidas(opcionesSeleccionadas || []);
		    
		    
		    console.log(opcionesSeleccionadas)
		}
	
	
	const manejarnombrejuego = (e) => {
			
			setnombrejuegop(e.target.value)
		};
		
		
	const manejardescripcion = (e) => {
			
			setdescripcion(e.target.value)
		};
		
		
		
		const insertarimagen = (e) => {
			
			let reader = new FileReader()
			reader.readAsDataURL(e.target.files[0])
			reader.onload = () => {
			setimagen(reader.result )
			
			}
		}

		

	

	
		const enviar = (e) => {
			
			if (companiasElegidas.length === 0) {
			        mostrarAlerta('warning', "Debes seleccionar al menos una compañia.");
			        return; 
			    }
			
		    let token = localStorage.getItem('token');
		    e.preventDefault();
		    
		    fetch(`${API_URL}/crearPropuesta`, {
		        method: 'POST', 
		        headers: {
		            'Content-Type': 'application/json',
		            'Authorization': 'Bearer ' + token 
		        },
		        body: JSON.stringify({
		            nombrejuego: nombrejuego, 
		            descripcionjuego: descripcion,
		            foto: imagen,
		            companiasJuego: companiasElegidas.map(empresa => ({
		                id: empresa.value,
		                name: empresa.label
		            })) 
		        }) 
		    }) 
		    .then(response => {
		        if (response.status === 200) {
		            mostrarAlerta('ok', "Solicitud enviada correctamente");
		            setTimeout(() => navigate('/'), 1500);
		        } 
		        else if (response.status === 402) {
		            mostrarAlerta('warning', "Este juego ya existe");
		        }
		        else {
		            mostrarAlerta('error', "Hubo un problema al crear la empresa: " + response.status);
		        }
		    })
		    .catch(error => {
		        console.error('Error en el fetch:', error);
		        mostrarAlerta('error', "No se pudo conectar con el servidor. Intentá de nuevo.");
		    });
		}
	


	return(

		<div className='crearjuego-page'>

		<div className='divprincipalcreajuego'>

			<div className='crearjuego-card'>

				<h1 className='crearjuego-titulo'>Proponer un juego</h1>
				<p className='crearjuego-subtitulo'>
					¿No encontrás el juego que buscás? Completá el formulario y un admin va a revisar tu solicitud.
				</p>

				<div className='crearjuego-alerta'>
					<AlertMessage
						tipo={alerta.tipo}
						mensaje={alerta.mensaje}
						onClose={cerrarAlerta}
					/>
				</div>

				<form onSubmit={enviar} className='crearjuego-form'>

					<div className='crearjuego-campo'>
						<label className='crearjuego-label'>Nombre del juego</label>
						<input
							className='crearjuego-input'
							placeholder='Ej: Red Dead Redemption 2'
							required
							onChange={manejarnombrejuego} />
					</div>

					<div className='crearjuego-campo'>
						<label className='crearjuego-label'>Descripción</label>
						<input
							className='crearjuego-input'
							placeholder='Contanos de qué trata el juego'
							required
							onChange={manejardescripcion} />
					</div>

					<div className='crearjuego-campo'>
						<label className='crearjuego-label'>Imagen de portada</label>

						<div className='crearjuego-imagen-row'>

							<div className='crearjuego-imagen-preview'>
								{imagen
									? <img className='crearjuego-imagen' src={imagen} alt='Portada del juego' />
									: <span className='crearjuego-imagen-placeholder'>Sin imagen</span>
								}
							</div>

							<label className='crearjuego-file-btn'>
								Elegir imagen
								<input
									className='crearjuego-file-input'
									required
									type='file'
									accept='image/*'
									onChange={insertarimagen}
								/>
							</label>

						</div>
					</div>

					<div className='crearjuego-campo'>
						<label className='crearjuego-label'>Compañías desarrolladoras</label>
						<Select
							isMulti
							classNamePrefix='crearjuego-select'
							placeholder='Buscá una compañía...'
							noOptionsMessage={() => 'No hay compañías disponibles'}
							options={listaEmpresas}
							onChange={manejarCambioOpcion}
						/>
					</div>

					<button type='submit' className='crearjuego-boton'>Enviar solicitud</button>

				</form>

			</div>

		</div>

		<Footer />

		</div>

	)

}

export default CrearJuegos
