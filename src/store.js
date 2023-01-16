// 액션
export const increase = (username) => ({ type: 'INCREAMENT', payload: username });
export const decrease = () => ({ type: 'DECREAMENT' });

// 상태
const initstate = {
  username: '',
  number: 1,
};

// 액션의 결과를 걸러내는 친구
const reducer = (state = initstate, action) => {
  switch (action.type) {
    case 'INCREAMENT':
      return { number: state.number + 1, username: action.payload }; // return 되면 호출한 쪽에서 받는게 아니라 return 되는 순간 변경됨
    case 'DECREAMENT':
      return { number: state.number - 1 };
    default:
      return state;
  }
};

export default reducer;
