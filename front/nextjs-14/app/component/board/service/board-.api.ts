import instance from "../../common/configs/axios-config"

export const findItemAllAPI = async () =>{
    try{
        const response = await instance().get('/item/list',{
            params: {limit: 10}
        })
        console.log(' 게시판 리턴 ...',response.data)
        return response.data
    }catch(error){
        console.log(error)
        return error
    }
}
export const findItemDetailAPI = async (data:any) =>{
    try{
        const response = await instance().post('/item/detail',data)
        console.log(' 게시판 리턴 ...',response.data)
        return response.data
    }catch(error){
        console.log(error)
        return error
    }
}