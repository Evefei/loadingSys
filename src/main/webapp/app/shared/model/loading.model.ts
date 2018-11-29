export interface ILoading {
  id?: number;
  loadingNo?: string;
  volumeUtilization?: number;
  weightUtilization?: number;
  materialQuantity?: number;
  materialVolume?: number;
  materialWeight?: number;
  lengthRemain?: number;
  widthRemain?: number;
  heightRemain?: number;
}

export const defaultValue: Readonly<ILoading> = {};
