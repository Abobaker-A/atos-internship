import { RolesDisplay } from './RolesDisplay';

export interface UserData {
    id?: string;
    firstName?: string;
    lastName: string;
    birthDate?: string;
    gender?: string;
    mobileNumber?: string;
    address?: {
        street?: string;
        city?: string;
        state?: string;
        zipCode?: string;
    };
    password?: string;
    email?: string;
    roles?:RolesDisplay[] ;
}

