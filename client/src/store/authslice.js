
import {  createSlice } from "@reduxjs/toolkit";
import { loadAuthState } from "./storage";
import { setToken } from "../lib/http";

export const authSlice =createSlice({
    name:'auth',
    initialState: loadAuthState(), 
    reducers:{
        
        loginSuccess:(state,action)=>{
            console.log(action)

            state.id=action.payload.user.id;
            // state.firstName=action.payload.firstName;
            state.email=action.payload.user.email;
            state.role = action.payload.user.role;
           setToken(action.payload.token.token)
        },

        logoutSuccess:(state)=>{

            state.id=0;
            // delete state.firstName
            delete state.email
            delete state.role
            setToken()
        },

       

    }
})


export const{ loginSuccess,logoutSuccess}=authSlice.actions;