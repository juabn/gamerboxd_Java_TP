import { useState } from 'react';
import './crearGrupo.css'
import '../../styles.css'
import FooterC from '../../components/Footer/Footer'

export default function CrearGrupo(){
	const [formData, setFormData] = useState({
	    nombre: '',
	    descripcion: ''
	  });

	  const handleChange = (e) => {
	    const { name, value } = e.target;
	    setFormData({
	      ...formData,
	      [name]: value
	    });
	  };

	  const handleSubmit = async (e) => {
	    e.preventDefault();

	    try {
	      const response = await fetch('http://localhost:8081/creargrupo', {
	        method: 'POST',
	        headers: {
	          'Content-Type': 'application/json',
	        },
	        body: JSON.stringify({
	          nombre: formData.nombre,
	          descripcion: formData.descripcion
	        }),
	      });

	      if (response.ok) {
	        alert('grupo insertado');
	        setFormData({ nombre: '', descripcion: '' }); // Resetea el form
	      } else {
	        alert('Error en el servidor al intentar crear el grupo.');
	      }
	    } catch (error) {
	      console.error('Fallo en la comunicación con el backend', error);
	    }
	  };
		
	  
	  return (
		<section className="body">
		    <div className="form-container">
		      <h2 className="form-title">Creacion de grupo</h2>
		      
		      <form onSubmit={handleSubmit} className="gaming-form">
		        <div className="input-group">
		          <label htmlFor="foto">Foto del Grupo</label>
		          <input 
		            type="file" 
		            id="foto" 
		            name="foto" 
		            accept="image/png, image/jpeg" 
		            onChange={handleChange} 
		            className="file-input"
		          />
		        </div>
	
		        <div className="input-group">
		          <label htmlFor="nombre">Nombre</label>
		          <input 
		            type="text" 
		            id="nombre" 
		            name="nombre" 
		            placeholder="PC Gamers" 
		            value={formData.nombre}
		            onChange={handleChange} 
		            required 
		          />
		        </div>
	
		        <div className="input-group">
		          <label htmlFor="descripcion">Descripción</label>
		          <textarea 
		            id="descripcion" 
		            name="descripcion" 
		            rows="5" 
		            placeholder="¿Qué tipo de juegos reseñan?..." 
		            value={formData.descripcion}
		            onChange={handleChange} 
		            required 
		          />
		        </div>
	
		        <button type="submit" className="submit-btn">Crear Grupo</button>
		      </form>
		    </div>
			<FooterC/>
		</section>
		
		
	  );
	};
	
	
