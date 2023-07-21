//초기화
const initalState = {
  user: {
    isLoggedIn: false,
    user: null,
    signUpdata: {},
    loginData: {},
  },
  post: {
    mainPosts: [],
  },
};

//로그인 액션
export const loginAction = (data) => {
  return {
    type: "LOG_IN",
    data,
  };
};

//reducer
const rootReducer = (state = initalState, action) => {
  switch (action.type) {
    case "LOG_IN":
      return {
        ...state,
        user: {
          ...state.user,
          isLoggedIn: true,
          user: action.data,
        },
      };
  }
};

export default rootReducer;
