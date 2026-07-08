import './GameCard.css';


function GameCard({ titulo, imagen }) {
	
	const manejarClick = () => {
	    console.log(titulo);
	  };
	
  return (
    <div className="card-juego">
     
      <img onClick={manejarClick} src={imagen} alt={titulo} className="card-img" />
      <div className="card-info">
        <h3 className="card-titulo">{titulo}</h3>
      </div>
    </div>
  );
}

export default GameCard;