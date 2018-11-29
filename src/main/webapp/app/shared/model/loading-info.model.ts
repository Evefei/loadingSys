import { ILoading } from 'app/shared/model//loading.model';

export interface ILoadingInfo {
  id?: number;
  step?: number;
  materialCode?: string;
  colorCode?: string;
  layoutType?: string;
  quantity?: number;
  row?: number;
  column?: number;
  layer?: number;
  lengthPosition?: number;
  widthPosition?: number;
  heightPosition?: number;
  length?: number;
  width?: number;
  height?: number;
  loadingId?: ILoading;
}

export const defaultValue: Readonly<ILoadingInfo> = {};
