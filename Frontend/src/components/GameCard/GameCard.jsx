import './GameCard.css';


function GameCard({ titulo, imagen }) {
	
  return (
    <div className="card-juego">
     
      <img src={imagen} alt={titulo} className="card-img" />
      <div className="card-info">
        <h3 className="card-titulo">{titulo}</h3>
      </div>
    </div>
  );
}

export default GameCard;