import { Avatar, Link, Typography } from "@mui/material";
import { GridColDef } from "@mui/x-data-grid";
import { itemColumns } from "../model/item-column";
import { PG } from "@/redux/common/enums/PG";


interface CellType{
    row : itemColumns
}


export default function BoardColumns(): GridColDef[]{
    
    return [
        {
            flex: 0.12,
            minWidth: 25,
            sortable: false,
            field: 'item',
            headerName: '종목',
            renderCell: ({row}:CellType) =>  (
                <div style={{ display: 'flex', alignItems: 'center' }}>
                <Typography>{row.item}</Typography>
              </div>
            )
        },
        {
            flex: 0.12,
            minWidth: 30,
            sortable: false,
            field: 'open',
            headerName: '시작가',
            renderCell: 
            ({row}:CellType) =>  (
                <div style={{ display: 'flex', alignItems: 'center' }}>
                <Typography>{row.open.toLocaleString()+'원'}</Typography>
              </div>
            )        },
        {
            flex: 0.12,
            minWidth: 30,
            sortable: false,
            field: 'high',
            headerName: '최고가',
            renderCell: 
            ({row}:CellType) =>  (
                <div style={{ display: 'flex', alignItems: 'center' }}>
                <Typography>{row.high.toLocaleString()+'원'}</Typography>
              </div>
            )        },
        {
            flex: 0.12,
            minWidth: 30,
            sortable: false,
            field: 'low',
            headerName: '최저가',
            renderCell: 
            ({row}:CellType) =>  (
                <div style={{ display: 'flex', alignItems: 'center' }}>
                <Typography>{row.low.toLocaleString()+'원'}</Typography>
              </div>
            )        },
        {
            flex: 0.12,
            minWidth: 30,
            sortable: false,
            field: 'close',
            headerName: '마감가',
            renderCell: 
            ({row}:CellType) =>  (
                <div style={{ display: 'flex', alignItems: 'center' }}>
                <Typography>{row.close.toLocaleString()+'원'}</Typography>
              </div>
            )        },
        {
            flex: 0.12,
            minWidth: 30,
            sortable: false,
            field: 'adjClose',
            headerName: '마감가 조정',
            renderCell: 
            ({row}:CellType) =>  (
                <div style={{ display: 'flex', alignItems: 'center' }}>
                <Typography>{row.adjClose.toLocaleString()+'원'}</Typography>
              </div>
            )        },
        {
            flex: 0.12,
            minWidth: 30,
            sortable: false,
            field: 'volume',
            headerName: '거래량',
            renderCell: 
            ({row}:CellType) =>  (
                <div style={{ display: 'flex', alignItems: 'center' }}>
                <Typography>{row.volume.toLocaleString()+'원'}</Typography>
              </div>
            )        },
        {
            flex: 0.30,
            minWidth: 30,
            sortable: false,
            field: 'date',
            headerName: '날짜',
            renderCell: 
            ({row}:CellType) =>  (
                <div style={{ display: 'flex', alignItems: 'center' }}>
                <Typography>{row.date}</Typography>
              </div>
            )        },
    ]

}