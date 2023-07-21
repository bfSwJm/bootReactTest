import React, { useEffect } from "react";
import { useDispatch } from "react-redux";
import AppLayout from "../components/AppLayout";
import { GET_TEST_APT_REQUEST } from "../reducers/user";

const index = () => {
  const dispatch = useDispatch();
  useEffect(() => {
    dispatch({
      type: GET_TEST_APT_REQUEST,
    });
  }, []);
  return (
    <AppLayout>
      <button>버튼</button>
      <h3>리스트</h3>
    </AppLayout>
  );
};

export default index;
