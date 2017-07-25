import React from 'react'
import { connect } from 'react-redux'

import * as actionCreators from 'actions'
import {NavBar} from 'components/NavBar'
import SideBar from 'components/SideBar'
import Footer from 'components/Footer'


class PageWithLeftBarLayoutCmp extends React.Component {
    constructor(props) {
        super(props)
    }


    render() {
        return <div className="wrapper">
            <SideBar />
            <div className="main-panel">
                <div className="content">
                    {this.props.children}
                </div>
                <Footer />
            </div>
        </div>
    }
}

function mapStateToProps(state) {
    return {
     }
}

export const PageWithLeftBarLayout = connect(mapStateToProps, actionCreators)(PageWithLeftBarLayoutCmp)