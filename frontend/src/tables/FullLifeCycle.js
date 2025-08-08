// src/tables/FullLifeCycle.js
import React, { useEffect, useState } from 'react';

function FullLifeCycle() {
  const [count, setCount] = useState(0);

  useEffect(() => {
    console.log('Component mounted or updated Count: {count}'); // Runs on mount + update

    return () => {
      console.log('Cleanup on unmount or before re-run Count: {count}'); // Runs on unmount
    };
  }); // No dependency array = runs on every render

  return (
    <div>
      <p>Count: {count}</p>
      <button onClick={() => setCount(count + 1)}>Increment</button>
      <button onClick={() => setCount(count - 1)}>Decrement</button>
    </div>
  );
}

export default FullLifeCycle;