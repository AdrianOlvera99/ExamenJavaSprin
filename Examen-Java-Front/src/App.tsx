import { useState } from 'react';
import { postSearchArrayFull, postSearchArray } from './api';

// Define la interfaz para la respuesta
interface ApiResponse {
  results: string[];
}

function App() {
  const [arrayA, setArrayA] = useState<string[]>([]);
  const [arrayB, setArrayB] = useState<string[]>([]);
  const [response, setResponse] = useState<ApiResponse | null>(null);
  const [productA, setProductA] = useState<string>('');
  const [productB, setProductB] = useState<string>('');

  const handlePost = async () => {
    try {
      const res = await postSearchArrayFull(arrayA, arrayB);
      setResponse(JSON.parse(res));
    } catch (error) {
      console.error(error);
    }
  };

  const handlePut = async () => {
    try {
      const res = await postSearchArray(arrayA, arrayB);
      setResponse(JSON.parse(res));
    } catch (error) {
      console.error(error);
    }
  };

  const addProductA = () => {
    if (productA.trim() !== '') {
      setArrayA([...arrayA, productA]);
      setProductA('');
    } else {
      alert('El producto A no puede estar vacío');
    }
  };

  const addProductB = () => {
    if (productB.trim() !== '') {
      setArrayB([...arrayB, productB]);
      setProductB('');
    } else {
      alert('El producto B no puede estar vacío');
    }
  };

  const handleKeyPressA = (event: React.KeyboardEvent<HTMLInputElement>) => {
    if (event.key === 'Enter') {
      addProductA();
    }
  };

  const handleKeyPressB = (event: React.KeyboardEvent<HTMLInputElement>) => {
    if (event.key === 'Enter') {
      addProductB();
    }
  };

  return (
    <div className="container mt-5">
      <h1 className="text-center">API Consumer</h1>
      <div className="row justify-content-center mb-3">
        <div className="col-auto">
          <button className="btn btn-secondary me-2" onClick={handlePost}>POST /api/searchArrayFull</button>
          <button className="btn btn-warning me-2" onClick={handlePut}>POST /api/searchArray</button>
        </div>
      </div>
      <div className="row justify-content-center">
        <div className="col">
          <h3 className="text-center mb-3">Response:</h3>
          <ul className="list-group">
            {response && response.results.map((item, index) => (
              <li key={index} className="list-group-item">{item}</li>
            ))}
          </ul>
        </div>
      </div>
      <div className="row mb-3">
        <div className="col">
          <label htmlFor="productA" className="form-label">Product A:</label>
          <div className="input-group">
            <input
              type="text"
              className="form-control"
              id="productA"
              value={productA}
              onChange={(e) => setProductA(e.target.value)}
              onKeyPress={handleKeyPressA}
            />
            <button className="btn btn-primary" onClick={addProductA}>Add</button>
          </div>
          <ul>
            {arrayA.map((item, index) => (
              <li key={index}>{item}</li>
            ))}
          </ul>
        </div>
        <div className="col">
          <label htmlFor="productB" className="form-label">Product B:</label>
          <div className="input-group">
            <input
              type="text"
              className="form-control"
              id="productB"
              value={productB}
              onChange={(e) => setProductB(e.target.value)}
              onKeyPress={handleKeyPressB}
            />
            <button className="btn btn-primary" onClick={addProductB}>Add</button>
          </div>
          <ul>
            {arrayB.map((item, index) => (
              <li key={index}>{item}</li>
            ))}
          </ul>
        </div>
      </div>
    </div>
  );
}

export default App;
