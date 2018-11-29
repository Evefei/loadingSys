import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ILoadingInfo, defaultValue } from 'app/shared/model/loading-info.model';

export const ACTION_TYPES = {
  FETCH_LOADINGINFO_LIST: 'loadingInfo/FETCH_LOADINGINFO_LIST',
  FETCH_LOADINGINFO: 'loadingInfo/FETCH_LOADINGINFO',
  CREATE_LOADINGINFO: 'loadingInfo/CREATE_LOADINGINFO',
  UPDATE_LOADINGINFO: 'loadingInfo/UPDATE_LOADINGINFO',
  DELETE_LOADINGINFO: 'loadingInfo/DELETE_LOADINGINFO',
  RESET: 'loadingInfo/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ILoadingInfo>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type LoadingInfoState = Readonly<typeof initialState>;

// Reducer

export default (state: LoadingInfoState = initialState, action): LoadingInfoState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_LOADINGINFO_LIST):
    case REQUEST(ACTION_TYPES.FETCH_LOADINGINFO):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_LOADINGINFO):
    case REQUEST(ACTION_TYPES.UPDATE_LOADINGINFO):
    case REQUEST(ACTION_TYPES.DELETE_LOADINGINFO):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_LOADINGINFO_LIST):
    case FAILURE(ACTION_TYPES.FETCH_LOADINGINFO):
    case FAILURE(ACTION_TYPES.CREATE_LOADINGINFO):
    case FAILURE(ACTION_TYPES.UPDATE_LOADINGINFO):
    case FAILURE(ACTION_TYPES.DELETE_LOADINGINFO):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_LOADINGINFO_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_LOADINGINFO):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_LOADINGINFO):
    case SUCCESS(ACTION_TYPES.UPDATE_LOADINGINFO):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_LOADINGINFO):
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

const apiUrl = 'api/loading-infos';

// Actions

export const getEntities: ICrudGetAllAction<ILoadingInfo> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_LOADINGINFO_LIST,
  payload: axios.get<ILoadingInfo>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<ILoadingInfo> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_LOADINGINFO,
    payload: axios.get<ILoadingInfo>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ILoadingInfo> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_LOADINGINFO,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ILoadingInfo> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_LOADINGINFO,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<ILoadingInfo> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_LOADINGINFO,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
