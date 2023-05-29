import { Address } from './address';

export interface RegisterUser {
  firstName?: string;
  lastName?: string;
  birthDate?: Date;
  gender?: string;
  email?: string;
  mobileNumber?: string;
  password?: string;
  address?: Address;
}
