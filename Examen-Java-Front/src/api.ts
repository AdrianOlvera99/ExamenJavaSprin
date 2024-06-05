import axios from 'axios';

export const getHello = async () => {
    const res = await axios.get('http://localhost:8080/api/hello');
    return res.data;
};

export const postSearchArrayFull = async (arrayA: string[], arrayB: string[]) => {
    const res = await axios.post('http://localhost:8080/api/searchArrayFull', { arrayA, arrayB });
    return JSON.stringify(res.data);
};

export const postSearchArray = async (arrayA: string[], arrayB: string[]) => {
    const res = await axios.post('http://localhost:8080/api/searchArray', { arrayA, arrayB });
    return JSON.stringify(res.data);
};
