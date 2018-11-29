import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ILoading, defaultValue } from 'app/shared/model/loading.model';

export const ACTION_TYPES = {
  FETCH_LOADING_LIST: 'loading/FETCH_LOADING_LIST',
  FETCH_LOADING: 'loading/FETCH_LOADING',
  CREATE_LOADING: 'loading/CREATE_LOADING',
  UPDATE_LOADING: 'loading/UPDATE_LOADING',
  DELETE_LOADING: 'loading/DELETE_LOADING',
  RESET: 'loading/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ILoading>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type LoadingState = Readonly<typeof initialState>;

// Reducer

export default (state: LoadingState = initialState, action): LoadingState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_LOADING_LIST):
    case REQUEST(ACTION_TYPES.FETCH_LOADING):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_LOADING):
    case REQUEST(ACTION_TYPES.UPDATE_LOADING):
    case REQUEST(ACTION_TYPES.DELETE_LOADING):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_LOADING_LIST):
    case FAILURE(ACTION_TYPES.FETCH_LOADING):
    case FAILURE(ACTION_TYPES.CREATE_LOADING):
    case FAILURE(ACTION_TYPES.UPDATE_LOADING):
    case FAILURE(ACTION_TYPES.DELETE_LOADING):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_LOADING_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_LOADING):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_LOADING):
    case SUCCESS(ACTION_TYPES.UPDATE_LOADING):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_LOADING):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {}
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState
      };
    default:
      return state;
  }
};

const apiUrl = 'api/loadings';

// Actions

export const getEntities: ICrudGetAllAction<ILoading> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_LOADING_LIST,
  payload: axios.get<ILoading>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<ILoading> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_LOADING,
    payload: axios.get<ILoading>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ILoading> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_LOADING,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ILoading> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_LOADING,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<ILoading> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_LOADING,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
