export interface IVehicle {
  id?: number;
  carLicenseNo?: string;
  length?: number;
  width?: number;
  height?: number;
  vehicleVolume?: number;
  vehicleWeight?: number;
  underpanHeight?: number;
  crankHeight?: number;
  crankWidth?: number;
}

export const defaultValue: Readonly<IVehicle> = {};
